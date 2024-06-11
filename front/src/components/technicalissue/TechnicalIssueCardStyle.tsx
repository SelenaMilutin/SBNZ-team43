import styled from "styled-components";

const borderRadius = "20px";

const TechnicalIssueCardStyle = styled.div`
    display: flex;
    flex-direction: column;
    justify-content: flex-start;
    align-items: center;
    border: 1px solid #bfbfbf;
    border-radius: ${borderRadius};
    width: 40vw;
    height: 60vh;
    padding: 50px 20px;
    box-shadow: 0 4px 8px rgba(0, 0.1, 0.1, 0.1);
    margin: 20px;
    overflow: hidden;
    opacity: 0.7;
    &:hover {
        opacity: 1;
    }

`

const IssuesAndSolutionsCardStyle = styled.div`
    display: flex;
    flex-direction: column;
    justify-content: space-between;
    border: 1px solid #bfbfbf;
    border-radius: 16px;
    width: 15vw;
    height: 15vw;
    padding: 20px 20px 20px 20px;
    box-shadow: 0 4px 8px rgba(0, 0.1, 0.1, 0.1);
    margin: 20px;
    transform: translate(0);
    transition: transform 0.5s ease;
    overflow: hidden;
    &:hover {
        transform: translate(2px, 1px);
        transition: transform 0.5s ease;
    }
    h5 {
        font-weight: normal;
        font-size: 14px;
        text-align: center;
    }

`

const TechnicalIssueCardContentStyle = styled.div`
    display: flex;
    flex-direction: row;
    justify-content: center;
`

const TechnicalIssueButtonStyle = styled.button`
    background-color: rgb(251, 247, 242);
    font-family: Arial, Helvetica, sans-serif;
    font-weight: 500;
    font-size: 16px;
    width: 240px;
    height: 80px;
    border: 0;
    border-radius: 7px;
    margin: 10px;
    transition: background-color 0.5s ease;
    cursor: pointer;
    &:hover {
        background-color: rgb(231, 227, 222);
    }
`



export { 
    TechnicalIssueCardStyle,
    TechnicalIssueButtonStyle,
    TechnicalIssueCardContentStyle,
    IssuesAndSolutionsCardStyle
};
