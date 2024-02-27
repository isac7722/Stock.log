import axios from "axios";


export const callNoteList = async ({journalId}) => {
    await axios("/notes",{
            journalId:journalId
    })
        .then(data => (data))
}