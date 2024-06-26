import { useEffect, useState } from "react";
import { useAuth } from "../../contexts/AuthContext";
import { ProfilePageStyle } from "./ProfilePageStyle";
import { UserService } from "../../services/UserService";
import { UserProfile } from "../../models/users/UserProfile";
import ContractCardList from "../../components/contract/ContractCardList";
import { ContractService } from "../../services/ContractService";
import { Contract } from "../../models/contracts/Contract";
import { CardContent, CardStyle } from "../../components/shared/Card/Card.style";
import { RowDiv } from "../../components/shared/style/DivStyle";
import { Packages } from "../../models/packages/Packages";
import { LinkStyle } from "../../components/shared/NavBar/NavBar.style";
import { CancellationDTO } from "../../models/contracts/CancelContract";

const ProfilePage = () => {

    const { user } = useAuth();
    const [userProfile, setUserProfile] = useState<UserProfile>();
    const [contracts, setContracts] = useState<Contract[]>([]);
    const [contractProposal, setContractProposal] = useState<Packages>();

    useEffect( () => {
        getProfile();
        getContracts();
        getContractProposal();
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

    function getContracts() {
        ContractService.getAllForClient()
        .then(
            (response) => {
                console.log(response);
                setContracts(response.data.map((contract: Contract) => ({
                    ...contract,
                    startDate: new Date(contract.startDate),
                    expirationDate: new Date(contract.expirationDate),
                  })))
            }
        ).catch(
            (error) => {
                alert("Couldn't load contracts.")
                console.error(error);
        })
    }

    function getContractProposal() {
        ContractService.getContractProposal()
        .then(
            (response) => {
                setContractProposal(response.data)
                console.log(response)
            }
        ).catch(
            (error) => {
        })
    }

    function onCancelContractClick(contractId: number) {
        console.log("cancelling contract with id " + contractId)
        ContractService.cancelContract(contractId)
        .then( 
            (response) => {
                let cancellation: CancellationDTO = response.data;
                if (cancellation.success === true) {
                    alert("You have cancelled contract, and you have debt of amount " + cancellation.value.toFixed(2) + ".")
                }
            }
        )
        .catch(
            (error) => {
                alert("Couldn't cancel contract.")
            }
        )
    }

    return (
        <ProfilePageStyle>
            <div>
                <h2>Profile overview</h2>
                <RowDiv>
                    <CardStyle>
                        <RowDiv>
                            <h3>{userProfile?.name}</h3>
                            <h3>{userProfile?.lastName}</h3>
                        </RowDiv>
                        <CardContent>
                            <p>Email: {userProfile?.email}</p>
                            <p>Address: {userProfile?.address}</p>
                            <p>Phone Number: {userProfile?.phone}</p>
                            <p>Blocked: {userProfile?.blockedFlag ? "yes" : "no"}</p>
                        </CardContent>
                    </CardStyle>
                    <CardStyle>
                        <h3>Service area information</h3>
                        <p>Service Area: {userProfile?.serviceAreaId}</p>
                        <p>Service Area Available: {userProfile?.serviceAreaAvailable ? "✅" : "❌"}</p>
                        <p>Service Area Current Capacity: {userProfile?.serviceAreaCurrentCapacity}</p>
                        <p>Service Area Maximum Capacity: {userProfile?.serviceAreaMaximumCapacity}</p>
                    </CardStyle>
                </RowDiv>
                
            </div>
            
            <div>
                <h2>Contracts</h2>
                {contractProposal !== undefined && contractProposal !== null && 
                    <div>
                        <h3>Contract recommendations</h3>
                        <p>You have proposal for {contractProposal.packageType.toLowerCase()} contract</p>
                        <CardStyle>
                            <h3>{contractProposal.name}</h3>
                            <CardContent>
                                <p>Monthly price: {contractProposal.monthlyPrice}</p>
                                <p>Go to<LinkStyle to="/contract/create">Create Contract page</LinkStyle></p>
                            </CardContent>
                        </CardStyle>
                    </div>
                }
                <h3>Contracts overview</h3>
                <ContractCardList contracts={contracts} cancelContractClick={onCancelContractClick}/>
            </div>

        </ProfilePageStyle>
    )

}

export default ProfilePage;