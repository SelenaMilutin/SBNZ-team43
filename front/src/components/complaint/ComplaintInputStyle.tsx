import styled from "styled-components";


const ComplaintInputStyle = styled.textarea `
    min-width: 40vw;
    max-width: 50vw;
    min-height: 150px;
    max-height: 200px
    margin-top: 20px;
    padding: 15px;
    font-size: 15px;
    font-family: Arial, Helvetica, sans-serif;
    outline: none;
    border: 0;
    border-bottom: 1px solid #bfbfbf;
    color: #bfbfbf;
    transition:color 0.5s ease;
    &:focus {
        color: #000000
    }
`

export {
    ComplaintInputStyle
}
