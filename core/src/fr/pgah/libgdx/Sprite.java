package fr.pgah.libgdx;

import java.util.Random;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Rectangle;

public class Sprite {

  Rectangle rekt;  //
  Texture img; // chaque objet a des valeurs indépendantes par rapport aux autres.
  Texture img2;
  int coordX;  // comportement (méthode), Etat (données, variable)
  int coordY;
  boolean versLaDroite;
  boolean versLeHaut;
  double facteurTaille;   // nombre flotant avec double
  int vitesse;
  float rotation;
  int vitesseRotation;
  Random generateurAleatoire;
  int longueurFenetre;
  int hauteurFenetre;
  int longueureffective;
  int hauteureffective;


  public Sprite(String img){  // définir un constructeur, jamais rien en type de retour (void ou return), le nom doit avoir le même nom que la classe ici Sprites. Son job est d'initialiser correctement tous les variables . 
     initialiser(img);
     rekt = new Rectangle(coordX, coordY, longueureffective, hauteureffective); 
  }  
 
  
  private void initialiser(String img) {

    
    longueurFenetre = Gdx.graphics.getWidth();
    hauteurFenetre = Gdx.graphics.getHeight();
    generateurAleatoire = new Random();
    this.img = new Texture(img);
    img2 = new Texture("Mario.png");
    facteurTaille = 0.15;
    vitesse = 1 + generateurAleatoire.nextInt(2);
    rotation = 0;
    vitesseRotation = 5 + generateurAleatoire.nextInt(5);
    versLaDroite = generateurAleatoire.nextBoolean();
    versLeHaut = generateurAleatoire.nextBoolean();
    coordX = generateurAleatoire.nextInt(longueurFenetre - longueureffective); // chercher le i eme sprites et utilisé . pour accéder aux attributs donc coordX 
    coordY = generateurAleatoire.nextInt(hauteurFenetre - hauteureffective); 
    longueureffective = (int) (this.img.getWidth() * facteurTaille);
    hauteureffective = (int) (this.img.getHeight() * facteurTaille);
   }
   
   public void maJEtat() {
    pivoter();
    deplacer();
    forcerAresterDansCadre();
  }

  public void pivoter() {
      rotation += vitesseRotation;
	  }

	 public void deplacer() {
     
      if (versLaDroite) {
        coordX += vitesse;
      } else {
         coordX -= vitesse;
      }
      if (versLeHaut) {
        coordY += vitesse;
      } else {
        coordY -= vitesse;
      }
    rekt.setPosition(coordX, coordY); // changer les coordonnées du rectangle
    }
    
	 public void forcerAresterDansCadre() {
    
      // Gestion bordure droite
      if (coordX + longueureffective > longueurFenetre) {
        coordX = longueurFenetre - longueureffective;
        versLaDroite = false;
      }

      // Gestion bordure gauche
      if (coordX < 0) {
        coordX = 0;
        versLaDroite = true;
      }

      // Gestion bordures haute
      if (coordY + hauteureffective > hauteurFenetre) {
        coordY = hauteurFenetre - hauteureffective;
        versLeHaut = false;
      }

      // Gestion bordure basse
      if (coordY < 0) {
        coordY = 0;
        versLeHaut = true;
      }
      rekt.setPosition(coordX, coordY); // changer les coordonnées du rectangle
  }

	 public void dessiner(Batch batch) {
    
    batch.draw(img,coordX, coordY,
        longueureffective / 2, hauteureffective / 2, longueureffective, hauteureffective,
        1, 1, rotation,
        0, 0, img.getWidth(), img.getHeight(),
        false, false);
      
        
  }
  public boolean seTrouveSur(Souris souris) {
    
      if (rekt.overlaps(souris.rekt)) {  //zone de collision de la souris
        return true;
    } else {
        return false;
   }
  }
    
}

