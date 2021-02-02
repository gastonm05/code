import React from 'react';
import {
	Text,
	View,
	StyleSheet,
	Button
} from 'react-native';
import {
	vibrate
} from './utils'

class Count extends React.Component {
	shouldComponentUpdate() {
		return !this.props.comp
	}
	render() {
		return ( <
			Text style = {
				{
					fontSize: 90,
					color: 'white'
				}
			} > {
				this.props.minutos
			}:{
				this.props.segundos
			} < /Text>
		);
	}
}
// constantes de tiempo
let segundosTimer = '00';
let minutosTimer = '00';
let resetvalTimer = '00';

export default class App extends React.Component {
	constructor() {
		super();
		this.state = {
			segundos: segundosTimer,
			minutos: minutosTimer,
			resetval: resetvalTimer,
			running: false,
			done: false,
			buttonTitle: 'Start',
			color: '#f44336'
		}
	}
	componentDidMount() {
		this.interval = setInterval(this.decrese, 1000)
	}
	decrese = () => {
		if (this.state.running & !this.state.done) {
			if (this.state.segundos > 0) {
				this.setState(prevState => ({
					segundos: String(parseInt(prevState.segundos) - 1)
				}))
				if (parseInt(this.state.segundos) < 10) {
					this.setState({
						segundos: '0' + String(this.state.segundos)
					})
				}
			} else {
				this.setState(prevState => ({
					minutos: String(parseInt(prevState.minutos) - 1),
					segundos: '59'
				}))
				if (parseInt(this.state.minutos) > 10) {
					this.setState({
						minutos: '' + String(this.state.minutos)
					})
				}
				if (parseInt(this.state.minutos) < 10) {
					this.setState({
						minutos: '0' + String(this.state.minutos)
					})
				}
			}
			if (parseInt(this.state.minutos) == '00' && parseInt(this.state.segundos) == '000') {
				this.setState({
					done: true,
					running: false
				})
				vibrate()
				console.log("¡Vibrando!");
			}
		}
	}

	resetTimer = () => {
		this.setState(prevState => ({
			minutos: prevState.resetval,
			segundos: '00'
		}))
		this.setState({
			done: false,
			running: false,
			buttonTitle: 'Start'
		})
	}
	startTimer = () => {
		if (this.state.minutos == '00' & this.state.segundos == '00') {
			this.setState({
				running: false
			})
		} else {
			if (this.state.running === false) {
				this.setState({
					running: true,
					buttonTitle: 'Stop'
				})
			} else {
				this.setState({
					running: false,
					buttonTitle: 'Continue'
				})
			}
		}
	}
	handlePause = () => {
		this.setState({
			running: false
		})
	}
	setWorkTime = () => {
		this.setState({
			minutos: '25',
			segundos: '00',
			resetval: '25',
			done: false,
			running: false,
			buttonTitle: 'Start',
			color: '#f44336'
		})
	}
	setShortBreak = () => {
		this.setState({
			minutos: '05',
			segundos: '00',
			resetval: '05',
			done: false,
			running: false,
			buttonTitle: 'Start',
			color: '#4CA6A9'
		})
	}
	setLongBreak = () => {
		this.setState({
			minutos: '15',
			segundos: '00',
			resetval: '15',
			done: false,
			running: false,
			buttonTitle: 'Start',
			color: '#498FC1'
		})
	}
	render() {
		return ( 
   <View style={[styles.container, styles.center, {backgroundColor: this.state.color}]}>
        <View style={{alignItems: 'center', flex: 8, justifyContent: 'center'}}>
          <View style={{  flexDirection: 'row', justifyContent: 'space-around', flex: 7, alignItems: 'center'}}>
          <Button title='Work'  onPress={this.setWorkTime} color='green' />
          <Button title='Short Break' onPress={this.setShortBreak} color='green'/>
          <Button title='Long Break'  onPress={this.setLongBreak}  color='green'/>
        </View>
          <Count minutos={this.state.minutos} segundos={this.state.segundos} comp={this.state.done}/>
        <View style={{ justifyContent: 'space-around', flexDirection: 'row', flex: 1, alignItems: 'center'}}/>
          <Button title={this.state.buttonTitle} onPress={this.startTimer} color='grey' />
          <Button title='Reset' onPress={this.resetTimer} color='black'/>
        </View>
        <View style={{  flex: 5}} >
        </View>
      </View>
    );
  }
}

const styles = StyleSheet.create({
	container: {
		flex: 8,
		justifyContent: 'center',
    
	}
})