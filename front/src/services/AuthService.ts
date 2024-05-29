import { HttpClient } from "./http/Http"

export class AuthService {

    private static url: string = "/auth"

    public static async login(username: string, password: string) {
        const loginUrl = `${this.url}/login`
        return HttpClient.post(loginUrl, {username, password})
    }

    public static async register(username: string, password: string, name: string, lastName: string, phone: string, address: string) {
        const loginUrl = `${this.url}/register`
        return HttpClient.post(loginUrl, {email: username, password, name, lastName, phone, address})
    }

}