import axios from "axios";
import { HTTP_SERVER_URL } from "../../constants/Constants";

interface Params {
    [key: string]: any;
  }

export class HttpClient  {

    private static baseUrl: string = HTTP_SERVER_URL+"/api"

    public static async get(url: string, params?: Params): Promise<any> {
        return axios.get(this.baseUrl.concat(url), { params })
    }

    public static async post(url: string, body: any, params?: Params): Promise<any> {
        return axios.post(this.baseUrl.concat(url), body, { params })
    }

    public static async put(url: string, body: any, params?: Params): Promise<any> {
        const config = { params };
        console.log(config);
        return axios.put(this.baseUrl.concat(url), body, config)
    }

    public static async delete(url: string, params?: Params): Promise<any> {
        return axios.delete(this.baseUrl.concat(url))
    }



}