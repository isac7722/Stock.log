const BACKEND_URL = process.env.BACKEND_URL;

const { accessToken } = useTokenStore();

// axios 기본 인스턴스 생성
const request = axios.create({
  baseURL: BACKEND_URL,
  withCredentials: true, // 기본 옵션으로 설정
  headers: {
    Authorization: `Bearer ${accessToken}`,
  },
});

