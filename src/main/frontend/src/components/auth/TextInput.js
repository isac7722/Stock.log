import React from 'react';
import '../../css/Auth.css';

const TextInput = ({ type, placeholder, value, onChange }) => {
    return (
        <div className='inputDiv'>
        <input className="authInput"
            type={type}
            placeholder={placeholder}
            value={value}
            onChange={onChange}
        />
        </div>
    );
};

export default TextInput;