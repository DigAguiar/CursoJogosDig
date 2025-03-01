package br.com.mariojp.game;

import java.awt.Image;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class Nave extends Sprite implements ImmunityState{
	private boolean immunityCurrentState;
	private int immunitySlots;
	private int dx;
	private int dy;

	private int alcance;
	
    private ArrayList<Missil> missiles;

	public Nave(int x, int y, int alcance) {
		super(x, y);
		this.alcance = alcance;
		initNave();
	}

	private void initNave() {
		missiles = new ArrayList<Missil>(); 
		carregarImagem("/imagens/nave.png"); 
		getImageDimensions();
	}

	public void move() {
		x += dx;
		y += dy;
	}
	

	// Implementação do State
	@Override
	public String immunity() {
		String msg = "Immunity slots are gone!";

		if (immunitySlots < 1) {
			msg = "Immunity On!";
			immunitySlots++;
			immunityCurrentState = true;
		}else {
			immunityCurrentState = false;
		}

		return msg;
	}

	public boolean getImmunityCurrentState () {
		return this.immunityCurrentState;
	}

	public void setImmunityStateEnds (boolean state) {
		this.immunityCurrentState = state;
		System.out.println("Immunity is over");
	}


	public Image getImage() {
		return image;
	}

	public void keyPressed(KeyEvent e) { 
		int key = e.getKeyCode();
		if (key == KeyEvent.VK_SPACE) {
			atira(); 
		}
		if (key == KeyEvent.VK_LEFT){ 
			dx= -1;
		}if(key==KeyEvent.VK_RIGHT){
			dx = 1;
		}if(key==KeyEvent.VK_UP){
			dy= -1;
		}if(key==KeyEvent.VK_DOWN){
			dy= 1;
		}
		// Para ativar imunidade
		if (key == KeyEvent.VK_I) {
			System.out.println(immunity());
			
		}
	}
	
	private void atira() {
		missiles.add(new Missil(x + width, y + height / 2, alcance ));		
	}

	public void keyReleased(KeyEvent e) { 
		int key = e.getKeyCode();
		if (key == KeyEvent.VK_LEFT){ 
			dx =0;
		}if(key==KeyEvent.VK_RIGHT){
			dx =0;
		}if(key==KeyEvent.VK_UP){
			dy=0;
		}if(key==KeyEvent.VK_DOWN){
			dy=0;
		}
		
	}

	public ArrayList<Missil> getMissiles() {
		return missiles;
	}

	
}
