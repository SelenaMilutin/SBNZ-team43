import { Contract } from "../../models/contracts/Contract";
import { formatDate } from "../../utils/formats/DateTimeFormat";
import { CardStyle, CardContent } from "../shared/Card/Card.style";

interface ContractCardProps {
    contract: Contract
}

const ContractCard = ({contract} : ContractCardProps) => {
    return <CardStyle>
                <h3>{contract.packageDTO.packageType} Package - {contract.packageDTO.name}</h3>
                <h4>Active (not-expired): {contract.activeFlag ? "✅" : "❌"}</h4>
                <CardContent>
                    <p>From: {formatDate(contract.startDate)}</p>
                    <p>To: {formatDate(contract.expirationDate)}</p>
                    <p>Monthly price: {contract.packageDTO.monthlyPrice}</p>
                    { contract.discount !==0 && <p>Discount: {100 * contract.discount}%</p>}
                </CardContent>
            </CardStyle>
}

export default ContractCard;