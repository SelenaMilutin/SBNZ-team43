import styled from "styled-components";

const InputDiv = styled.div`
    margin: 25px 0;
    display: flex;
    flex-direction: column;
`

const StyledInput = styled.input`
    width: 100%;
    min-height: 35px;
    padding: 0;
    font-size: 15px;
    outline: none;
    border: 0;
    border-bottom: 1px solid #bfbfbf;
    color: #bfbfbf;
    transition:color 0.5s ease;
    &:focus {
        color: #000000
    }
`

const Label = styled.label`
    font-weight: 500;
`

const ChooseFileLabel = styled.label`
    font-weight: 600;
    padding: 0.5em;
    border: thin solid grey;
    border-radius: 6px;
    cursor: pointer;
    height: 25px;
`

export {
    InputDiv,
    Label,
    ChooseFileLabel
}

export default StyledInput;