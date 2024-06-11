import { HttpClient } from "./http/Http"

export class ServiceAreaService {

    private static url: string = "/servicearea"

    public static async getAll() {
        return HttpClient.get(this.url)
    }

    public static async toggleAvailability(serviceAreaId: number, available: boolean) {
        const finalUrl = `${this.url}/setavailable`
        return HttpClient.put(finalUrl, null, {serviceAreaId, available});
    }

}