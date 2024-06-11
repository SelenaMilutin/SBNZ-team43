import { Packages } from "../../models/packages/Packages";
import CardListStyle from "../shared/CardList/CardList.style";
import PackageCard from "./PackageCard";

interface PackageCardListProps {
    packages: Packages[]
    selectPackage: (packages: Packages) => void
}

const PackageCardList = ({packages, selectPackage} : PackageCardListProps) => {

    return (
        <CardListStyle>
            {packages.map(onepackages => (
                <PackageCard 
                    packages={onepackages}
                    selectPackage={selectPackage}
                    key={onepackages.id}
                />
            ))}
        </CardListStyle>
    )

}

export default PackageCardList;