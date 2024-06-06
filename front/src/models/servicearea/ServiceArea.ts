export interface ServiceArea {
    id: number,
    activeFlag: boolean,
    availableFlag: boolean,
    backupFlag: boolean,
    maximumCapacity: boolean,
    currentCapacity: boolean,
    lastUnavailableTimestamp: Date
}