import {useState} from "react";
import Form from 'react-bootstrap/Form';
import Button from "react-bootstrap/Button";


const JournalSearcher = () => {

    const [text, setText] = useState("");

    const searcherHandler = (e) => {
        setText(e.target.value)
    };
    console.log(text);


    return (
        <>
            <Form.Control
                type={"text"}
                id={"search"}
                value={text}
                onChange={searcherHandler}
            />
            <Button type={"submit"}>검색</Button>
        </>
    )
}

export default JournalSearcher;