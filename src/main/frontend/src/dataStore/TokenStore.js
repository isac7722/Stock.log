// ./stores/memos.js
import { create } from "zustand";

export const useTokenStore = create((set) => ({
  accessToken: '',
  setAccessToken: (value) => set({ accessToken: value }),
  refreshToken: '',
  setRefreshToken: (value) => set({ refreshToken: value }),
}));
