import React,{Component} from "react";
import CakeTerm from "../CakeTerm/cakeTerm";
import ReactPaginate from "react-paginate";




class CakeList extends Component{
    constructor(props) {
        super(props);
        this.state = {
            page: 0,
            size: 5
        }
    }
    render () {
        const offset = this.state.size * this.state.page;
        const nextPageOffset = offset + this.state.size;
        const pageCount = Math.ceil(this.props.books.length / this.state.size);
        const cakes = this.getCakesPage(offset, nextPageOffset);
        return (

            <div className={"container mm-4 mt-5"}>
                <div className={"row"}>
                    <div className={"table-responsive"}>
                        <table className={"table table-striped"}>
                            <thead>
                            <tr>
                                <th scope={"col"}>Name</th>
                                <th scope={"col"}>Description</th>
                                <th scope={"col"}>Price</th>
                                <th scope={"col"}>Available Sales</th>
                            </tr>
                            </thead>
                            <tbody>
                            {cakes}
                            </tbody>

                        </table>
                    </div>
                </div>
                <ReactPaginate previousLabel={"back"}
                               nextLabel={"next"}
                               breakLabel={<a href="/#">...</a>}
                               breakClassName={"break-me"}
                               pageClassName={"ml-1"}
                               pageCount={pageCount}
                               marginPagesDisplayed={2}
                               pageRangeDisplayed={5}
                               onPageChange={this.handlePageClick}
                               containerClassName={"pagination m-4 justify-content-center"}
                               activeClassName={"active"}/>
            </div>

        )
    }
    handlePageClick = (data) => {
        let selected = data.selected;
        this.setState({
            page: selected
        })
    }

    getCakesPage = (offset, nextPageOffset) => {
        return this.props.cakes.map((term, index) => {
            return (
                <CakeTerm term={term} />
            );
        }).filter((product, index) => {
            return index >= offset && index < nextPageOffset;
        })

    }

}
export default CakeList;
