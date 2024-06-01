import { useEffect, useState } from "react";
import { useAuth } from "../../contexts/AuthContext";
import Button from "../../components/shared/Button/Button";
import Input from "../../components/shared/Input/Input";
import { AuthService } from "../../services/AuthService";
import { ProfilePageStyle } from "./ProfilePageStyle";
import { UserService } from "../../services/UserService";
import { UserProfile } from "../../models/users/UserProfile";

const ProfilePage = () => {

    const { user } = useAuth();
    const [userProfile, setUserProfile] = useState<UserProfile>();
    
    useEffect( () => {
        getProfile();
    }, [])

    function getProfile() {
        UserService.getProfile(user!.username)
        .then(
            (response) => {
                setUserProfile(response.data)
                console.log(response)
            }
        ).catch(
            (error) => {
                console.log(error);
        })
    }

    return (
        <ProfilePageStyle>
            <h2>Profile</h2>
            <p>Email: {userProfile?.email}</p>
            <p>Name: {userProfile?.name}</p>
            <p>Last Name: {userProfile?.lastName}</p>
            <p>Address: {userProfile?.address}</p>
            <p>Phone Number: {userProfile?.phone}</p>
            <p>Blocked: {userProfile?.blockedFlag ? "yes" : "no"}</p>
            <p>Service Area: {userProfile?.serviceAreaId}</p>
            <p>Service Area Available: {userProfile?.serviceAreaAvailable ? "yes" : "no"}</p>
            <p>Service Area Current Capacity: {userProfile?.serviceAreaCurrentCapacity}</p>
            <p>Service Area Maximum Capacity: {userProfile?.serviceAreaMaximumCapacity}</p>
        </ProfilePageStyle>
    )

}

export default ProfilePage;