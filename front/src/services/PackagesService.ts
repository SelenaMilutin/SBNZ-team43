import { PackageType } from "../models/packages/Packages";
import { HttpClient } from "./http/Http"

export class PackagesService {

    private static url: string = "/packages"

    public static async getAvailablePackages(packageType: PackageType) {
        return HttpClient.get(this.url, {packageType})
    }

}