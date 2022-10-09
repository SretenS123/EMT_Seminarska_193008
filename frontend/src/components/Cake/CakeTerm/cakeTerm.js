import React from "react";




const CakeTerm = (props) => {
    return (
        <tr>
            <td >{props.term.name}</td>
            <td >{props.term.description}</td>
            <td >{props.term.price}</td>
            <td >{props.term.sales}</td>

        </tr>
    )
}
export default CakeTerm;