import { CreateContract } from "../models/contracts/CreateContract";
import { HttpClient } from "./http/Http"

export class ContractService {

    private static url: string = "/contracts"

    public static async getAllForClient() {
        return HttpClient.get(this.url)
    }

    public static async postNewContract(contract: CreateContract) {
        return HttpClient.post(this.url, contract, {} );
    }

    public static async getContractProposal() {
        const finalUrl = `${this.url}/proposals`
        return HttpClient.get(finalUrl)
    }

    public static async getDiscount() {
        const finalUrl = `${this.url}/discounts`
        return HttpClient.get(finalUrl);
    }

    public static async cancelContract(contractId: number) {
        const finalUrl = `/cancellation/${contractId}`
        return HttpClient.post(finalUrl, null, {} );
    }

    public static async getReport() {
        const finalUrl = `${this.url}/pyCartReport`
        return HttpClient.get(finalUrl);
    }
}