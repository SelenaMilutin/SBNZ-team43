import styled from "styled-components";

const MultiselectStyle = styled.div`
    display: flex;
    flex-direction: row;
    justify-content: flex-start;
    align-content: flex-start;
    > * {
        margin: 10px 15px;
    }
`

const Select = styled.select`
    height: 30px;
    width: 250px;
`

const RemoveSelectedButton = styled.button`
    height: 25px;
    width: 25px;
    margin: 5px;
`
const SelectedDiv = styled.div`
    display: flex;
    flex-direction: column;
`

export {
    MultiselectStyle,
    Select,
    RemoveSelectedButton,
    SelectedDiv
}