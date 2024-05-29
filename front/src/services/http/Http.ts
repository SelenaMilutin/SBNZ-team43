import axios from "axios";

interface Params {
    [key: string]: any;
  }

export class HttpClient  {

    private static baseUrl: string = "http://localhost:8081/api"

    public static async get(url: string, params?: Params): Promise<any> {
        return axios.get(this.baseUrl.concat(url), { params })
    }

    public static async post(url: string, body: any, params?: Params): Promise<any> {
        return axios.post(this.baseUrl.concat(url), body)
    }

    public static async put(url: string, body: any, params?: Params): Promise<any> {
        return axios.put(this.baseUrl.concat(url), body)
    }

    public static async delete(url: string, params?: Params): Promise<any> {
        return axios.delete(this.baseUrl.concat(url))
    }



}