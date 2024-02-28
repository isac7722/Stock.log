import axios from "axios";

const DOMAIN = "http://localhost:8080";

const getAccessToken = () => {
  // localStorage에서 accessToken 가져오기
  const accessToken = localStorage.getItem("accessToken");

  return accessToken ? `BEARER ${accessToken}` : "";
};

export const request = async (method, url, data) => {
  const headers = {
    Authorization: getAccessToken(),
  };

  return await axios({
    method,
    url: `${DOMAIN}${url}`,
    data,
    headers,
  })
    .then((res) => res.data)
    .catch((error) => console.log(error));
};