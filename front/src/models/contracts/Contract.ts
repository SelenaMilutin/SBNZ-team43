import { Packages } from "../packages/Packages";

export interface Contract {
    id: number,
    startDate: Date,
    expirationDate: Date,
    activeFlag: boolean,
    packageDTO: Packages,
    discount: number
}