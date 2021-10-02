import React, {useEffect, useState} from 'react';
import DatePicker from "react-datepicker";
import ReactModal from "react-modal";
import Select from "react-select";

import "./TrainingCapture.css";
import {StandardButton} from "../../common/button/standard-button/StandardButton";
import {retrieveAllTrainingModules} from "../../../services/TrainingModuleService";
import {TrainingCaptureModal} from "./training-capture-modal/TrainingCaptureModal";
import {
    createTrainingPackage,
    deleteTrainingPackage,
    retrieveAllTrainingGroups,
    updateTrainingPackage,
} from "../../../services/TrainingService";
import {retrieveAllGrants} from "../../../services/GrantService";
import {TrainingFileUploadButton} from "../../common/button/file-upload-button/TrainingFileUploadButton";

export const TrainingCapture = ({context, setSpinnerText, showToaster, navigateTo, navigateBack}) => {

    ReactModal.setAppElement('#root');

    const [showTrainingModal, setShowTrainingModal] = useState(false);
    const [dateOfTraining, setDateOfTraining] = useState(null);
    const [trainingModuleList, setTrainingModuleList] = useState([]);
    const [location, setLocation] = useState(null);
    const [retrievedGrantList, setRetrievedGrantList] = useState(null);
    const [selectedGrant, setSelectedGrant] = useState(null);
    const [retrievedTrainingGroupList, setRetrievedTrainingGroupList] = useState([]);
    const [selectedTrainingGroupList, setSelectedTrainingGroupList] = useState(null);
    const [file, setFile] = useState(null);
    const [fileList, setFileList] = useState([]);
    const [numberOfPeopleTrained, setNumberOfPeopleTrained] = useState(null);
    const [selectedLevelOfTrainingList, setSelectedLevelOfTrainingList] = useState([]);
    const [levelOfTrainingList] = useState([
        {value: "National", label: "National"},
        {value: "Provincial", label: "Provincial"},
        {value: "District", label: "District"},
        {value: "Sub District", label: "Sub District"},
        {value: "Facility", label: "Facility"},
        {value: "Other", label: "Other"},
    ]);

    useEffect(() => {
        const fetchGroups = async () => {
            const [data, error] = await retrieveAllTrainingGroups(context.username);
            if (data) {
                if (data.responseStatus === "Success") {
                    const mappedGroups = data.trainingGroupVOList.map((group) => {
                        return {value: group.externalReference, label: group.name}
                    });
                    setRetrievedTrainingGroupList(mappedGroups);
                } else {
                    showToaster(data);
                }
            } else {
                showToaster({
                    responseStatus: "Error",
                    responseMessage: error.message,
                });
            }
        }
        fetchGroups();

        const fetchGrants = async () => {
            const [data, error] = await retrieveAllGrants(context.username);
            if (data) {
                if (data.responseStatus === "Success") {
                    const mappedGrants = data.grantVOS.map((grant) => {
                        return {value: grant.externalReference, label: grant.name}
                    });
                    setRetrievedGrantList(mappedGrants);
                } else {
                    showToaster(data);
                }
            } else {
                showToaster({
                    responseStatus: "Error",
                    responseMessage: error.message,
                });
            }
        }
        fetchGrants();

        const fetchTrainingModules = async () => {
            const [data] = await retrieveAllTrainingModules(context.username);
            if (data) {
                if (data.responseStatus === "Success") {
                    let mappedTrainingModules = data.trainingModuleVOList.map((trainingModule) => {
                        return {...trainingModule, isSelected: false, moduleName: trainingModule.name};
                    });
                    if (!context.newTrainingSelected) {
                        mappedTrainingModules.forEach((trainingModule) => {
                            context.selectedTraining.module.forEach((selectedTrainingModule) => {
                                if (trainingModule.externalReference === selectedTrainingModule.externalReference) {
                                    trainingModule.isSelected = true;
                                }
                            })
                        })
                    }
                    setTrainingModuleList(mappedTrainingModules);
                }
            }
        };
        fetchTrainingModules();

        if (context.newTrainingSelected) {
            setDateOfTraining(null);
            setLocation("");
            setFile(null);
            setSelectedGrant("");
            setNumberOfPeopleTrained("");
            setSelectedTrainingGroupList([])
            setSelectedLevelOfTrainingList([]);
        } else {
            if (context.selectedTraining) {
                setLocation(context.selectedTraining.trainingLocation);
                setNumberOfPeopleTrained(context.selectedTraining.numberOfPeopleTrained);
                setDateOfTraining(new Date(context.selectedTraining.startDate));
                if (context.selectedTraining.grant) {
                    const mappedGrant = {
                        value: context.selectedTraining.grant.externalReference,
                        label: context.selectedTraining.grant.name,
                    }
                    setSelectedGrant(mappedGrant);
                } else {
                    setSelectedGrant(null);
                }
                const mappedSelectedGroups = context.selectedTraining.trainingGroupVOS.map((group) => {
                    return {value: group.externalReference, label: group.name}
                });
                setSelectedTrainingGroupList(mappedSelectedGroups);
                const levelOfTrainingList = context.selectedTraining.levelOfTraining.map((levelOfTrainingStr) => {
                    return {value: levelOfTrainingStr, label: levelOfTrainingStr};
                });
                setSelectedLevelOfTrainingList(levelOfTrainingList);
            }
        }
    }, []);

    useEffect(() => {
        if (null !== file) {
            if (!fileList.some(fileInList => fileInList.name === file.name)) {
                setFileList((prevState) => ([...prevState, file]));
            }
        }
    }, [file]);

    const handleTrainingProvidedClick = () => {
        setShowTrainingModal(true);
    }

    const handleCreateOrUpdateTrainingClick = async () => {
        setSpinnerText("Saving Training");
        let selectedTrainingModules = trainingModuleList.filter(trainingModule => trainingModule.isSelected);
        let grant = null;
        if (selectedGrant) {
            grant = {
                externalReference: selectedGrant.value,
            };
        }
        let trainingPackageExtRef = null;
        if (!context.newTrainingSelected) {
            trainingPackageExtRef = context.selectedTraining.externalReference;
        }
        let trainingPackage = {
            numberOfPeopleTrained: numberOfPeopleTrained,
            trainingLocation: location,
            startDate: dateOfTraining,
            trainingGroupVOS: selectedTrainingGroupList.map((group) => {
                return {externalReference: group.value};
            }),
            grant,
            module: selectedTrainingModules.map((module) => {
                return {externalReference: module.externalReference};
            }),
            levelOfTraining: selectedLevelOfTrainingList.map((levelOfTraining) => {
                return levelOfTraining.label;
            }),
            trainingFiles: fileList,
            externalReference: trainingPackageExtRef
        }
        if (context.newTrainingSelected) {
            const [data, error] = await createTrainingPackage(context.username, trainingPackage);
            if (data) {
                if (data.responseStatus === "Success") {
                    await resetScreen();
                }
                showToaster(data);
            } else {
                showToaster({
                    responseStatus: "Error",
                    responseMessage: error.message,
                });
            }

            setSpinnerText(null);
            navigateTo("trainingSearch");

        } else {
            const [data, error] = await updateTrainingPackage(context.username, trainingPackage);
            if (data) {
                if (data.responseStatus === "Success") {
                    await resetScreen();
                }
                showToaster(data);
            } else {
                showToaster({
                    responseStatus: "Error",
                    responseMessage: error.message,
                });
            }

            setSpinnerText(null);
            navigateTo("trainingSearch");
        }
    }

    const handleDeleteTrainingClick = async () => {
        setSpinnerText("Deleting Training");
        let trainingDeleted = false;
        const [data, error] = await deleteTrainingPackage(context.username, context.selectedTraining.externalReference);
        if (data) {
            if (data.responseStatus === "Success") {
                trainingDeleted = true;
            }
            showToaster(data);
        } else {
            showToaster({
                responseStatus: "Error",
                responseMessage: error.message,
            });
        }
        if (trainingDeleted) {
            navigateTo("trainingSearch");
        }
        setSpinnerText(null);
    }

    const handleFileDownloadClick = (fileToView) => {
        window.open("https://qitdev.auruminstitute.org:58081/api/file/downloadFile?externalReference=" + fileToView.externalReference);
    }

    const resetScreen = async () => {
        setDateOfTraining(null);
        setLocation("");
        setFile(null);
        setNumberOfPeopleTrained("");
        setSelectedLevelOfTrainingList(null);

        const copyOfTrainingModuleList = JSON.parse(JSON.stringify(trainingModuleList));
        const mappedTrainingModuleList = copyOfTrainingModuleList.map((trainingModule) => {
            return {...trainingModule, isSelected: false};
        })
        setTrainingModuleList([...mappedTrainingModuleList]);
    }

    const handleTrainingModalButtonClick = () => {
        setShowTrainingModal(false);
    }

    return (
        <div className="training-capture-container">
            <div className="training-input-container">
                <div className="input-container">
                    <label>Date of Training:</label>
                    <DatePicker selected={dateOfTraining}
                                className="text-input"
                                dateFormat="dd/MM/yyy"
                                showPopperArrow={false}
                                placeholderText="Select..."
                                onChange={(selectedDate) => {
                                    setDateOfTraining(selectedDate);
                                }}/>
                </div>
                <div className="input-container">
                    <label>Location:</label>
                    <input className="text-input"
                           type="text"
                           value={location}
                           onChange={({target}) => {
                               setLocation(target.value)
                           }}/>
                </div>
                <div className="training-capture-person-container">
                    <label className="label">Grant:</label>
                    <div className="input-container training-select-container">
                        <Select options={retrievedGrantList}
                                closeMenuOnSelect={true}
                                onChange={(selectedGrantClick) => {
                                    setSelectedGrant(selectedGrantClick)
                                }}
                                value={selectedGrant}
                                classNamePrefix="select"/>
                    </div>
                </div>
                <div className="training-capture-person-container">
                    <label className="label">Group(s) Trained:</label>
                    <div className="input-container training-select-container">
                        <Select options={retrievedTrainingGroupList}
                                isMulti
                                closeMenuOnSelect={false}
                                onChange={(selectedGroup) => {
                                    setSelectedTrainingGroupList(selectedGroup)
                                }}
                                value={selectedTrainingGroupList}
                                classNamePrefix="select"/>
                    </div>
                </div>
                <div className="input-container">
                    <label>Number of People Trained:</label>
                    <input className="text-input"
                           value={numberOfPeopleTrained}
                           type="text"
                           onChange={({target}) => {
                               const numberOnlyRegExp = /^[0-9]*$/;
                               if (numberOnlyRegExp.test(target.value)) {
                                   setNumberOfPeopleTrained(target.value)
                               }
                           }}/>
                </div>
                <div className="input-container">
                    <label>Level of Training:</label>
                    <Select options={levelOfTrainingList}
                            isMulti
                            closeMenuOnSelect={false}
                            onChange={(selectedLevel) => {
                                setSelectedLevelOfTrainingList(selectedLevel)
                            }}
                            value={selectedLevelOfTrainingList}
                            classNamePrefix="select"/>
                </div>
            </div>
            <div className="training-capture-button-container">
                <StandardButton text="Training Provided"
                                onClick={handleTrainingProvidedClick}/>
                <TrainingFileUploadButton buttonText="Upload Register"
                                          setFile={setFile}/>
                {!context.newTrainingSelected &&
                <StandardButton text="Download Register"
                                onClick={() => {
                                    handleFileDownloadClick(file)
                                }}/>}
            </div>
            <div className="training-capture-create-button-container">
                {!context.newTrainingSelected && <StandardButton text="Back" onClick={navigateBack}/>}
                {!context.newTrainingSelected && <StandardButton text="Delete Training"
                                                                 onClick={handleDeleteTrainingClick}/>}
                <StandardButton text={!context.newTrainingSelected ? "Save Training" : "Create Training"}
                                onClick={handleCreateOrUpdateTrainingClick}
                                disabled={!file}/>
            </div>

            <ReactModal isOpen={showTrainingModal} className="modal-content" overlayClassName="modal-overlay">
                <TrainingCaptureModal trainingModuleList={trainingModuleList}
                                      setTrainingModuleList={setTrainingModuleList}
                                      onUpdateClick={handleTrainingModalButtonClick}/>
            </ReactModal>
        </div>
    );
}
