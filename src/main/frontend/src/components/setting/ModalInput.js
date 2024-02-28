
/*모달 안에 들어갈 input*/

const ModalInput = ({ type, placeholder, value, onChange }) => {
    return(
        <div>
            <input className="changePasswordInput"
            type={type}
            placeholder={placeholder}
            value={value}
            onChange={onChange} />
        </div>
    )
}

export default ModalInput;