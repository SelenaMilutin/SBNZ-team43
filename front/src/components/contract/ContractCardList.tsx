import { Contract } from "../../models/contracts/Contract";
import CardListStyle from "../shared/CardList/CardList.style";
import ContractCard from "./ContractCard";

interface ContractCardListProps {
    contracts: Contract[]
    cancelContractClick: (contractId: number) => void;
}

const ContractCardList = ({contracts, cancelContractClick} : ContractCardListProps) => {

    return (
        <CardListStyle>
            {contracts.map(contract => (
                <ContractCard 
                    contract={contract}
                    key={contract.id}
                    cancelContractClick={cancelContractClick}
                />
            ))}
        </CardListStyle>
    )

}

export default ContractCardList;