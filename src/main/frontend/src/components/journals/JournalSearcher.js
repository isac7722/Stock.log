import {useState} from "react";


const JournalSearcher = () => {

    const [text, setText] = useState("");

    const searcherHandler = (e) => {
        setText(e.target.value)
    };
    console.log(text);


    return (
        <>

        </>
    )
}

export default JournalSearcher;