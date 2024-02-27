import { useState } from 'react';
import '../../css/setting/Setting.css';
import Button from "react-bootstrap/Button";
import { Modal } from 'react-bootstrap';
import ChangePassInput from './ChangePassInput';

const ChangePassBtn = () => {

    const [show, setShow] = useState(false);

    const handleClose = () => setShow(false);
    const handleShow = () => setShow(true);

    const [password, setPassword] = useState('');

    const passwordOnchangeHandler = (e) => {
        setPassword(e.target.value);
    }

    const [checkPassword, setCheckPassword] = useState('');

    const checkPasswordOnchangeHandler = (e) => {
        setCheckPassword(e.target.value);
    }

    return(
        <>
        <Button id='changePasswordButton' onClick={handleShow}>비밀번호 변경</Button>

        <Modal show={show} onHide={handleClose}>
        <Modal.Header closeButton>
          <Modal.Title>변경할 비밀번호를 입력해주세요.</Modal.Title>
        </Modal.Header>
        <Modal.Body>

            <ChangePassInput
                placeholder = "변경할 비밀번호를 입력해주세요"
                type="password"
                onChange={passwordOnchangeHandler}
                value={password}
            />

            <ChangePassInput
                placeholder = "변경할 비밀번호를 다시 입력해주세요" 
                type="password"
                onChange={checkPasswordOnchangeHandler}
                value={checkPassword}
            />
        </Modal.Body>
        <Modal.Footer>
            <Button id='changePasswordButton' onClick={handleClose}>
                비밀번호 변경
            </Button>
            <Button id='changePasswordButton' onClick={handleClose}>
                취소
            </Button>
        </Modal.Footer>
      </Modal>
        </>
    )
}

export default ChangePassBtn;