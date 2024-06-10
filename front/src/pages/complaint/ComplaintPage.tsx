import { SetStateAction, useEffect, useState } from "react";
import Input from "../../components/shared/Input/Input";
import { ComplaintPageStyle } from "./ComplaintPageStyle";
import Button from "../../components/shared/Button/Button";
import { ComplaintsService } from "../../services/ComplaintsService";
import ComplaintInput from "../../components/complaint/ComplaintInput";
import Multiselect from "../../components/shared/Multiselect/Multiselect";
import { Packages, PackageType } from "../../models/packages/Packages";
import { Contract } from "../../models/contracts/Contract";
import { ContractService } from "../../services/ContractService";

interface PackageOption {id: string, name: string, monthlyPrice?: number, inOfferFlag?: boolean, packageType?: PackageType}


const ComplaintPage = () => {

    const [packagesOptions, setPackages] = useState<PackageOption[]>([]);
    const [selectedPackages, setSelectedPackages] = useState<PackageOption[]>([]);
    const [complaintText, setComplaintText] = useState<string>("");
    const [recommendationText, setRecommendationText] = useState<string>("");

    useEffect( () => {
        getContracts();
    }, [])

    function getContracts() {
        ContractService.getAllForClient()
        .then(
            (response) => {
                console.log(response);
                let received = response.data.map((contract: Contract) => ({
                    ...contract.packageDTO,
                    id: contract.packageDTO.id.toString()
                  }))
                console.log(received)
                setPackages(received)
            }
        ).catch(
            (error) => {
                alert("Couldn't load contracts.")
                console.error(error);
        })
    }

    function submit() {
        if (complaintText !== "" && selectedPackages.length > 0) {
            ComplaintsService.postComplaint(parseInt(selectedPackages[0].id), complaintText, recommendationText)
            .then(response => {
                alert("Succesfully submitted complaint.")
            }).catch(error => {
                alert("Couldn't submit complaint. Try again later.")
            })
        }
    }

    const handlePackageSelectionChange = (val: SetStateAction<PackageOption[]>) => {
        console.log("Setting selected package: ", val)
        setSelectedPackages(val); 
    };
    
    return <ComplaintPageStyle>

        <h2>Send a complaint for a service</h2>
        <h3>Help us improve our services by providing feedback.</h3>

        <p>Which package is the complaint referring to?</p>
        { packagesOptions.length > 0 && 
            <Multiselect 
                isSingleSelect={true}
                data={packagesOptions} 
                values={[]} 
                setSelectedInParent={handlePackageSelectionChange}/>
        }
        <ComplaintInput inputType="text" label="Write complaint" setValue={setComplaintText}/>
        <ComplaintInput inputType="text" label="Write recommendation for improving service" setValue={handlePackageSelectionChange}/>
        <Button text="Submit" type="submit" onClickHandler={submit}/>
    </ComplaintPageStyle>

}

export default ComplaintPage;