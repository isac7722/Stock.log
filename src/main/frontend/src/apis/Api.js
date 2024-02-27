

// const DOMAIN = "http://localhost:4000";

export const request = async (method, url, data, domain) =>{
    return await axios({
        method,
        url:`${domain}${url}`,
        data
    }).then(res => res.data)
    .catch(error => console.log(error));
};
