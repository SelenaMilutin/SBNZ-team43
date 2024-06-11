import { HttpClient } from "./http/Http"

export class ComplaintsService {

    private static url: string = "/complaints"

    public static async getIssueSolution(issueConsequence: string) {
        const finalUrl = `${this.url}/technicalissues`
        return HttpClient.get(finalUrl, {issueConsequence})
    }

    public static async postComplaint(packageId: number, complaint: string, recommendation: string) {
        return HttpClient.post(this.url, {packageId, complaint, recommendation}, {})
    }


}