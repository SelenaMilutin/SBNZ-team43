import { SetStateAction, useEffect, useState } from "react";
import Multiselect from "../../components/shared/Multiselect/Multiselect";
import { CreateContractPageStyle } from "./CreateContractPageStyle";
import PackageCardList from "../../components/packages/PackageCardList";
import { Packages, PackageType } from "../../models/packages/Packages";
import { PackagesService } from "../../services/PackagesService";
import Button from "../../components/shared/Button/Button";
import { ContractService } from "../../services/ContractService";


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

    const handleContractLenghtSelectionChange = (val: SetStateAction<ContractLengthOption[]>) => {
        setSelectedContractLengthOptions(val); 
    };

    useEffect( () => {
        GetPackagesInOffer('MOBILE');
        GetPackagesInOffer('NET');
        GetPackagesInOffer('CABLE');
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
            <h2>Join millions of users - welcome to Internet Services!</h2>
                <h3>Select length of contract</h3>
                <Multiselect 
                    isSingleSelect={true}
                    data={contractLengthOptions} 
                    setSelectedInParent={handleContractLenghtSelectionChange} 
                    values={[]} />
                <h3>Choose package category and type</h3>
                <h4>Net packages</h4>
                <PackageCardList packages={netPackages} selectPackage={onPackageSelect}/>
                <h4>Mobile packages</h4>
                <PackageCardList packages={mobilePackages} selectPackage={onPackageSelect}/>
                <h4>Cable packages</h4>
                <PackageCardList packages={cablePackages} selectPackage={onPackageSelect}/>
                <Button text={"Create contract"} type={"submit"} onClickHandler={onSubmitClick} />
            </CreateContractPageStyle>

}

export default CreateContractPage;