import '../../css/setting/Setting.css';

const SettingInput = ( { placeholder } ) => {
    return(
        <div class="form-floating mb-3">
           <input type="email" className='settingEmailInput' placeholder={placeholder} disabled/>
        </div>
    )
}

export default SettingInput;