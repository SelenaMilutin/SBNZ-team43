export interface UserProfile {
    name: string,
    lastName: string,
    phone: string,
    email: string,
    address: string,
    blockedFlag: boolean,
    serviceAreaId: string,
    serviceAreaAvailable: string,
    serviceAreaCurrentCapacity: number,
    serviceAreaMaximumCapacity: number,
}