import axios from "../custom-axios/axios";

const CakeService ={
    fetchCakes: () => {
        return axios.get("/cakes",{
            params: {
                size: 5,
                batch: 2
            }
        });
    }
}

export default CakeService;