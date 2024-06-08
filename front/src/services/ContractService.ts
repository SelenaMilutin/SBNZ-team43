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

}