import './App.css';
import React, {Component} from "react"
import {BrowserRouter as Router, Route,Navigate,Routes} from "react-router-dom";
import CakeService from "../../repository/CakeRepository";
import Header from "../../components/Header/header"
import Cakes from "../Cake/CakeList/cakeList"



class App extends Component {
  constructor(props) {
    super(props);
    this.state = {
      cakes: []
    }
  }

  render() {
    return (
        <Router>
          <main>
            <Header/>
            <div className="container">
              <Routes>
                <Route path={"/cakes"} element={<Cakes cakes={this.state.cakes}/>}/>
                {/*<Route path={"/books/add"} element={<BookAdd categories={this.state.categories}*/}
                {/*                                             authors={this.state.authors}*/}
                {/*                                             onBookAdd={this.addBook}/>}/>*/}
                {/*<Route path={"/books/edit/:id"}*/}
                {/*       element={<BookEdit onEditBook={this.editBook} book={this.state.selectedBook}*/}
                {/*                          categories={this.state.categories} authors={this.state.authors}/>}/>*/}
                {/*<Route path={"/books"}*/}
                {/*       element={<Books books={this.state.books} onDelete={this.deleteBook} onEdit={this.getBook}*/}
                {/*                       onMarkAsTaken={this.markAsTaken}/>}/>*/}
                <Route path="/" element={<Navigate to="/books" replace/>}/>
              </Routes>


            </div>
          </main>
        </Router>

    );
  }

  loadCakes = () => {
    CakeService.fetchCakes()
        .then((data) => {
          this.setState({
            cakes: data.data
          })
        });
  }

  componentDidMount() {
    this.loadCakes();

  }
}
export default App;
