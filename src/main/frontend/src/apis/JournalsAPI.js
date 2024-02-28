

// 매매일지 목록 조회
export const callJournalList = async () => {
    return await request.get("/journals/search");
  };
  
  // 매매일지 검색
  export const callJournalListBySearch = async ({ data }) => {
    return await request.post("/journals/search", { data });
  };
