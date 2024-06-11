import { HttpClient } from "./http/Http"

export class UserService {

    private static url: string = "/user"

    public static async getProfile(username: string) {
        return HttpClient.get(this.url, {username})
    }

}