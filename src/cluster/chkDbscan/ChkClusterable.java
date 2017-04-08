/**
 * 
 */
package cluster.chkDbscan;

import process.CheckinChain;

/**
 * @author tianqin
 *
 */
public interface ChkClusterable {
	
	//Return all the checkin informations.
	CheckinChain getPoint();

}
