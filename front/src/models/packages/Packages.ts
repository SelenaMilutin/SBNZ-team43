export interface Packages {
    id: number,
    name: string,
    monthlyPrice: number,
    packageType: PackageType,
    inOfferFlag: boolean
}

export type PackageType = 'MOBILE' | 'CABLE' | 'NET'