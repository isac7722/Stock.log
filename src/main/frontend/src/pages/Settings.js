import SettingButton from "../components/setting/SettingButton";
import SettingInput from "../components/setting/SettingInput";

const Settings = () => {

    return (
        <div className="settingDiv">
            <SettingInput
                placeholder={"email"}
           />
            <SettingInput
                placeholder={"비밀번호"}
           />
           <SettingButton
                value={"비밀번호 변경"}
           />
        </div>
    )
}
export default Settings;