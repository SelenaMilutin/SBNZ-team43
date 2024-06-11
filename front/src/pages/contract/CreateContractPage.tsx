import { SetStateAction, useEffect, useState } from "react";
import Multiselect from "../../components/shared/Multiselect/Multiselect";
import { CreateContractPageStyle } from "./CreateContractPageStyle";
import PackageCardList from "../../components/packages/PackageCardList";
import { Packages, PackageType } from "../../models/packages/Packages";
import { PackagesService } from "../../services/PackagesService";
import Button from "../../components/shared/Button/Button";
import { ContractService } from "../../services/ContractService";
import { CenteredDiv, RowDiv, RowDivTopAligned } from "../../components/shared/style/DivStyle";
import { CardStyle } from "../../components/shared/Card/Card.style";
import { IssuesAndSolutionsCardStyle } from "../../components/technicalissue/TechnicalIssueCardStyle";


const availableContractLengthOptions: ContractLengthOption[] = [
    {id: "1", name: "6 months"  , length: 6, },
    {id: "2",  name: "12 months", length: 12,},
    {id: "3",  name: "24 months", length: 24,},
]

interface ContractLengthOption {id: string, name: string, length?: number}

const CreateContractPage = () => {

    const [contractLengthOptions, setContractLengthOptions] = useState<ContractLengthOption[]>(availableContractLengthOptions);
    const [selectedContractLengthOptions, setSelectedContractLengthOptions] = useState<ContractLengthOption[]>([]);
    const [selectedPackage, setSelectedPackage] = useState<Packages>();
    const [mobilePackages, setMobilePackages] = useState<Packages[]>([]);
    const [netPackages, setNetPackages] = useState<Packages[]>([]);
    const [cablePackages, setCablePackages] = useState<Packages[]>([]);
    const [discount, setDiscount] = useState<number | null>(null);
    const [isVisibleNet, setIsVisibleNet] = useState(false);
    const [isVisibleMobile, setIsVisibleMobile] = useState(false);
    const [isVisibleCable, setIsVisibleCable] = useState(false);

    const toggleVisibility = (id: string) => {
        switch(id) {
            case "NET": setIsVisibleNet(!isVisibleNet); return;
            case "MOBILE": setIsVisibleMobile(!isVisibleMobile); return;
            case "CABLE": setIsVisibleCable(!isVisibleCable); return;
        }
    };

    const handleContractLenghtSelectionChange = (val: SetStateAction<ContractLengthOption[]>) => {
        setSelectedContractLengthOptions(val); 
    };

    useEffect( () => {
        GetPackagesInOffer('MOBILE');
        GetPackagesInOffer('NET');
        GetPackagesInOffer('CABLE');
        GetDiscount();
    }, [])

    async function GetPackagesInOffer(type: PackageType) {
        try {
            const res = await PackagesService.getAvailablePackages(type);
            if(type === 'NET') setNetPackages(res.data);
            if(type === 'MOBILE') setMobilePackages(res.data);
            if(type === 'CABLE') setCablePackages(res.data);
            
            console.log(res)
        } catch (error) {
            console.log(error);
        }
    }

    async function GetDiscount() {
        try {
            const res = await ContractService.getDiscount();
            setDiscount(res.data)
            console.log(res)
        } catch (error) {
            console.log(error);
        }
    }

    function onPackageSelect(packages: Packages) {
        setSelectedPackage(packages);
    }

    function onSubmitClick() {
        console.log(selectedPackage)
        console.log(selectedContractLengthOptions)
        if (selectedContractLengthOptions.length > 0 && selectedContractLengthOptions[0].length !== undefined) {
            ContractService.postNewContract(
                {packageId: selectedPackage!.id, 
                lengthInMonths: selectedContractLengthOptions[0].length
            }).then( response => {
                alert("Successfuly created contract. Review it in profile section.")
                console.log(response)
            }).catch((error) => {
                console.error(error)
                alert("Couldn't create contract, try again later")})
        }
    }

    return <CreateContractPageStyle>
            <h1>Create a new contract</h1>

            <h3>Select length of contract</h3>
            <div>
                    <Multiselect 
                        isSingleSelect={true}
                        data={contractLengthOptions} 
                        setSelectedInParent={handleContractLenghtSelectionChange} 
                        values={[]} />
                </div>

            <h3>Choose package from categories</h3>
            <div style={{ marginLeft: '50px'}}>
                { selectedPackage != null && 
                    <p>Selected package: {selectedPackage.name} </p>
                    
                }
                <h3 onClick={() => {toggleVisibility("NET")}} style={{ cursor: 'pointer' }}>Net packages ˅</h3>
                { isVisibleNet && <PackageCardList packages={netPackages} selectPackage={onPackageSelect}/>}
                <h3 onClick={() => {toggleVisibility("MOBILE")}} style={{ cursor: 'pointer' }}>Mobile packages ˅</h3>
                { isVisibleMobile &&  <PackageCardList packages={mobilePackages} selectPackage={onPackageSelect}/>}
                <h3 onClick={() => {toggleVisibility("CABLE")}} style={{ cursor: 'pointer' }}>Cable packages ˅</h3>
                { isVisibleCable &&  <PackageCardList packages={cablePackages} selectPackage={onPackageSelect}/>}
            </div>
                
                
                
                
                
                { selectedPackage != null && selectedContractLengthOptions.length>0 &&
                    <div>
                        <hr style={{marginTop: '40px'}}/>
                        <CenteredDiv>
                            <p>You have selected following contract options: </p>
                            { discount !== null && <p>Additionaly you have discount of {100 * discount}%</p>}
                            <CardStyle>
                                <h4>• Package {selectedPackage?.name}</h4>
                                <h4>• For {selectedContractLengthOptions[0]?.name} months</h4>
                                { discount !== null && <h4>• With discount {100 * discount}%</h4> }
                            </CardStyle>
                            <Button text={"Create contract"} type={"submit"} onClickHandler={onSubmitClick} />
                        </CenteredDiv>
                    </div>
                }
            </CreateContractPageStyle>

}

export default CreateContractPage;