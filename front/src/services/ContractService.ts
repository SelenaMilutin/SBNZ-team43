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

}