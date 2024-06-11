import { Contract } from "../../models/contracts/Contract";
import { formatDate } from "../../utils/formats/DateTimeFormat";
import Button from "../shared/Button/Button";
import { CardStyle, CardContent } from "../shared/Card/Card.style";
import { RightAlignDiv } from "../shared/style/DivStyle";

interface ContractCardProps {
    contract: Contract
    cancelContractClick: (contractId: number) => void;
}

const ContractCard = ({contract, cancelContractClick} : ContractCardProps) => {

    return <CardStyle>
                <h3>{contract.packageDTO.packageType} Package - {contract.packageDTO.name}</h3>
                <h4>Active (not-expired): {contract.activeFlag ? "✅" : "❌"}</h4>
                <CardContent>
                    <p>From: {formatDate(contract.startDate)}</p>
                    <p>To: {formatDate(contract.expirationDate)}</p>
                    <p>Monthly price: {contract.packageDTO.monthlyPrice}</p>
                    { contract.discount !==0 && <p>Discount: {100 * contract.discount}%</p>}
                    <RightAlignDiv>
                        {   contract.activeFlag && 
                            <Button text="Cancel contract" type="button" onClickHandler={() => {cancelContractClick(contract.id)}}/>
                        }
                    </RightAlignDiv>
                </CardContent>
            </CardStyle>
}

export default ContractCard;