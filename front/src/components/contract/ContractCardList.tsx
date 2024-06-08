import { Contract } from "../../models/contracts/Contract";
import CardListStyle from "../shared/CardList/CardList.style";
import ContractCard from "./ContractCard";

interface ContractCardListProps {
    contracts: Contract[]
}

const ContractCardList = ({contracts} : ContractCardListProps) => {

    return (
        <CardListStyle>
            {contracts.map(contract => (
                <ContractCard 
                    contract={contract}
                    key={contract.id}
                />
            ))}
        </CardListStyle>
    )

}

export default ContractCardList;