import { Packages } from "../../models/packages/Packages";
import Button from "../shared/Button/Button";
import { CardStyle, CardContent } from "../shared/Card/Card.style";
import { RightAlignDiv } from "../shared/style/DivStyle";

interface PackageCardProps {
    packages: Packages
    selectPackage: (packages: Packages) => void;
}

const PackageCard = ({packages, selectPackage} : PackageCardProps) => {
    return <CardStyle>
                <h3>{packages.name}</h3>
                <CardContent>
                    <p>Monthly price: {packages.monthlyPrice}</p>
                </CardContent>
                <RightAlignDiv>
                    <Button text="Select" type="button" onClickHandler={ () => {selectPackage(packages)} }/>
                </RightAlignDiv>
            </CardStyle>
}

export default PackageCard;