import axios from "axios";

const BACKEND_URL = process.env.BACKEND_URL;
const accessToken = localStorage.getItem("accessToken")

// axios 기본 인스턴스 생성
export const request = axios.create({
  baseURL: BACKEND_URL,
  withCredentials: true, // 기본 옵션으로 설정
  headers: {
    Authorization: `BEARER ${accessToken}`,
  },
});

