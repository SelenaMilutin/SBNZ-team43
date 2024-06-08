import { useEffect, useState } from "react";
import { IssueSolution } from "../../models/technicalissue/IssueSolution";
import { TechnicalIssuesPageStyle } from "./TechnicalIssuesStyle";
import { ComplaintsService } from "../../services/ComplaintsService";
import { RowDiv } from "../../components/shared/style/DivStyle";
import { CardContent, CardStyle } from "../../components/shared/Card/Card.style";
import { IssuesAndSolutionsCardStyle, TechnicalIssueCardContentStyle, TechnicalIssueCardStyle } from "../../components/technicalissue/TechnicalIssueCardStyle";
import TechnicalIssueButton from "../../components/technicalissue/TechnicalIssueButton";
import CardListStyle from "../../components/shared/CardList/CardList.style";

const TechnicalIssuesPage = () => {

    const [issueSolutionList, setIssueSolutionList] = useState<IssueSolution[]>([]);
    // const [selectedIssue, setSelectedIssue] = useState<string>("");

    async function getIssueSolutions(selectedIssue: string) {
        try {
            const res = await ComplaintsService.getIssueSolution(selectedIssue)
            console.log(res)
            setIssueSolutionList(res.data)
        } catch {
            alert("Couldn't get solutions. Try again later.")
        }
    }

    return <TechnicalIssuesPageStyle>
        <h2>Get help diagnosing a technical issue</h2>
        <h3>By providing feedback</h3>
        <RowDiv>
            <TechnicalIssueCardStyle>
                <h3>Internet problems</h3>
                <TechnicalIssueCardContentStyle>
                    <div>
                        <TechnicalIssueButton text="Router not working" type="submit" onClickHandler={() => {getIssueSolutions("Router not working")}}/>
                        <TechnicalIssueButton text="Router activity lamp indicator not on" type="submit" onClickHandler={() => {getIssueSolutions("Router activity lamp indicator not on")}}/>
                        <TechnicalIssueButton text="Router not powered on" type="submit" onClickHandler={() => {getIssueSolutions("Router not powered on")}}/>

                    </div>
                    <div>
                        <TechnicalIssueButton text="Unavailable internet connection" type="submit" onClickHandler={() => {getIssueSolutions("Unavailable internet connection")}}/>
                        <TechnicalIssueButton text="Device is not connected to router's wifi" type="submit" onClickHandler={() => {getIssueSolutions("Device is not connected to router's wifi")}}/>
                        <TechnicalIssueButton text="Can't establish connection with service area" type="submit" onClickHandler={() => {getIssueSolutions("Can't establish connection with service area")}}/>
                    </div>
                </TechnicalIssueCardContentStyle>
            </TechnicalIssueCardStyle>
            <TechnicalIssueCardStyle>
                <h3>SService and signal range problems</h3>
                <TechnicalIssueCardContentStyle>
                    <div>
                        <TechnicalIssueButton text="No signal reception" type="submit" onClickHandler={() => {getIssueSolutions("No signal reception")}}/>
                        <TechnicalIssueButton text="Phone can't receive reception" type="submit" onClickHandler={() => {getIssueSolutions("Phone can't receive reception")}}/>
                    </div>
                    <div>
                        <TechnicalIssueButton text="Low signal range" type="submit" onClickHandler={() => {getIssueSolutions("Low signal range")}}/>
                    </div>
                </TechnicalIssueCardContentStyle>
            </TechnicalIssueCardStyle>
        </RowDiv>
        <h3>Possible issues & Solutions to try</h3>
        <CardListStyle>
            {issueSolutionList.map(solution => 
                <IssuesAndSolutionsCardStyle>
                    <h5>{solution.issue}</h5>
                    <h5><b>Try:</b> {solution.solution}</h5>
                </IssuesAndSolutionsCardStyle>)}
        </CardListStyle>
    </TechnicalIssuesPageStyle>
}

export default TechnicalIssuesPage;