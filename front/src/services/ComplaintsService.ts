import { HttpClient } from "./http/Http"

export class ComplaintsService {

    private static url: string = "/complaints"

    public static async getIssueSolution(issueConsequence: string) {
        const finalUrl = `${this.url}/technicalissues`
        return HttpClient.get(finalUrl, {issueConsequence})
    }


}